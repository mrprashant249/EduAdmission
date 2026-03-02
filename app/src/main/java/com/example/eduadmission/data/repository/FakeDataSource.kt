package com.example.eduadmission.data.repository

import com.example.eduadmission.data.model.Course
import com.example.eduadmission.data.model.University

object FakeDataSource {
    val universities = listOf(
        University("1", "Gujarat University", "Gujarat, India", "https://cdn.dnaindia.com/sites/default/files/2019/05/05/820131-gujarat-university.jpg", 4.9f),
        University("2", "Saurashtra University", "Gujarat, India", "https://saurashtrauniversity.ac.in/img/about.jpg", 4.6f),
        University("3", "Stanford University", "California, USA", "https://assets.simpleviewinc.com/simpleview/image/upload/c_fill,h_396,q_75,w_704/v1/crm/sanmateoca/shutterstock_4189008910-9b68011a5056a36_9b6802fa-5056-a36a-0bbb53c8e971b411.jpg", 4.5f),
        University("4", "MIT", "Massachusetts, USA", "https://news.mit.edu/sites/default/files/images/202309/MIT-USNews-Colleges-01-press.jpg", 4.4f),
        University("5", "Harvard University", "Massachusetts, USA", "https://stubard.com/wp-content/uploads/2025/02/Harvard-University-1-1200x812.jpg", 4.3f),
        University("6", "North Gujarat University", "Gujarat, India", "https://images.bhaskarassets.com/thumb/1200x900/web2images/960/2025/06/19/7A6A9C7A-ADAD-4BC1-8326-B9EDBFF145B5_1750325192084.jpg", 4.5f),
        University("7", "Oxford University", "Oxford, UK", "https://dynamic-media.tacdn.com/media/photo-o/31/d2/78/ef/caption.jpg?f=webp&w=1000&h=700", 4.2f)
    )

    val courses = listOf(
        Course("c1", "1", "Computer Science", "4 Years", "₹2,50,000/yr", "World-class CS program focusing on AI and Systems."),
        Course("c2", "1", "MBA", "2 Years", "₹1,70,000/yr", "Top-tier business school with global networking."),
        Course("c3", "1", "History", "3 Years", "₹40,000/yr", "Fundamental & Classic humanities program covering world history."),
        Course("c4", "1", "Law", "3 Years", "₹1,55,000/yr", "Advance & Prestigious law degree with focus on International Law."),
        Course("c5", "1", "Artificial Intelligence", "2 Years", "₹2,25,000/yr", "Professional & Specialization Advanced AI research and development."),
        Course("c6", "2", "Artificial Intelligence", "2 Years", "₹2,40,000/yr", "Official Advanced AI research and development."),
        Course("c7", "2", "MBA", "2 Years", "₹1,58,000/yr", "Most Value Top-tier business school with global networking."),
        Course("c8", "2", "Law", "3 Years", "₹1,65,000/yr", "Advance & Prestigious law degree with focus on International Law."),
        Course("c9", "3", "Law", "3 Years", "$55,000/yr", "Prestigious law degree with focus on International Law."),
        Course("c10", "3", "MBA", "2 Years", "$1,70,000/yr", "Top-tier business school with global networking."),
        Course("c11", "4", "History", "3 Years", "$40,000/yr", "Classic humanities program covering world history."),
        Course("c12", "4", "MBA", "3 Years", "$1,55,000/yr", "Value Top-tier business school with global networking."),
        Course("c13", "4", "Computer Science", "5 Years", "₹1,95,000/yr", "CS program focusing on Essential Advanced AI research and development."),
        Course("c14", "5", "MBA", "2 Years", "$1,90,000/yr", "Global Top-tier business school with global networking."),
        Course("c15", "5", "History", "2 Years", "$50,000/yr", "All humanities program covering world history."),
        Course("c16", "6", "Computer Science", "4 Years", "₹2,40,000/yr", "Best CS program focusing on Essential Advanced AI research and development."),
        Course("c17", "6", "Law", "3 Years", "₹1,25,000/yr", "Advance & Prestigious law degree with focus on International Law."),
        Course("c18", "7", "Artificial Intelligence", "3 Years", "$95,000/yr", "Professional Advanced AI research and development."),
        Course("c19", "7", "MBA", "3 Years", "$70,000/yr", "One of the Top-tier business school with global networking."),
        Course("c20", "7", "Law", "2 Years", "$65,000/yr", "World-class Advance & Prestigious law degree with focus on International Law."),
    )
}
