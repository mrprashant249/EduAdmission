package com.example.eduadmission.ui.application

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.eduadmission.EduApp
import com.example.eduadmission.R
import com.example.eduadmission.databinding.FragmentStatusBinding
import com.example.eduadmission.ui.adapter.ApplicationAdapter
import com.example.eduadmission.viewmodel.ApplicationViewModel
import com.example.eduadmission.viewmodel.EduViewModelFactory

class StatusFragment : Fragment(R.layout.fragment_status) {

    private val viewModel: ApplicationViewModel by activityViewModels {
        EduViewModelFactory((requireActivity().application as EduApp).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentStatusBinding.bind(view)

        val adapter = ApplicationAdapter { appId, newStatus ->
            viewModel.updateApplicationStatus(appId, newStatus)
        }
        binding.rvApplications.layoutManager = LinearLayoutManager(context)
        binding.rvApplications.adapter = adapter

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val app = adapter.getItem(position)

                if (direction == ItemTouchHelper.LEFT) {

                    AlertDialog.Builder(requireContext())
                        .setTitle(getString(R.string.delete_application))
                        .setMessage(getString(R.string.delete_message))
                        .setPositiveButton(getString(R.string.yes)) { _, _ ->
                            viewModel.deleteApplication(app.applicationId)
                        }
                        .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                            // Restore the item view immediately
                            adapter.notifyItemChanged(position)
                            dialog.dismiss()
                        }
                        .setCancelable(false)
                        .show()
                } else if (direction == ItemTouchHelper.RIGHT) {
                     // Update Status (Swipe Left to Right)
                    val nextStatus = when (app.status) {
                        getString(R.string.submitted) -> getString(R.string.under_review)
                        getString(R.string.under_review) -> getString(R.string.accepted)
                        getString(R.string.accepted) -> getString(R.string.rejected)
                        else -> getString(R.string.submitted)
                    }
                    viewModel.updateApplicationStatus(app.applicationId, nextStatus)
                    
                    // Immediately restore the view to its unswiped state
                    adapter.notifyItemChanged(position)
                }
            }


            override fun onChildDraw(
                c: android.graphics.Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView
                val background = android.graphics.drawable.ColorDrawable()
                val paint = android.graphics.Paint()
                paint.color = android.graphics.Color.WHITE
                paint.textSize = 40f
                paint.textAlign = android.graphics.Paint.Align.CENTER
                
                val context = context ?: return

                if (dX > 0) { // Swiping Left to Right (Update)
                    background.color = androidx.core.content.ContextCompat.getColor(context, R.color.status_submitted) 
                    background.setBounds(itemView.left, itemView.top, itemView.left + dX.toInt(), itemView.bottom)
                    background.draw(c)
                    val text = getString(R.string.update_status)
                    c.drawText(text, itemView.left + dX/2, itemView.top + (itemView.height / 2f), paint)
                } else if (dX < 0) { // Swiping Right to Left (Delete)
                    background.color = android.graphics.Color.RED
                    background.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
                    background.draw(c)
                    val text = getString(R.string.delete_application)
                    c.drawText(text, itemView.right + dX/2, itemView.top + (itemView.height / 2f), paint)
                } 
                
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }
        
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.rvApplications)

        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val userEmail = sharedPref.getString("user_email", null)

        if (userEmail != null) {
            viewModel.getApplications(userEmail).observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }
    }
}
