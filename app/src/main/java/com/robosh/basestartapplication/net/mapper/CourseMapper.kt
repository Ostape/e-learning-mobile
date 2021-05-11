package com.robosh.basestartapplication.net.mapper

import com.robosh.basestartapplication.model.Comment
import com.robosh.basestartapplication.model.Course
import com.robosh.basestartapplication.model.Lesson
import com.robosh.basestartapplication.net.model.CourseResponse
import javax.inject.Inject

class CourseMapper @Inject constructor() {

    private companion object {
        const val DEFAULT_IMAGE_LESSON =
            "https://www.corporatecomplianceinsights.com/wp-content/uploads/2020/08/lesson.jpg"
    }

    fun map(courseResponse: CourseResponse): Course {

        val lessons = courseResponse.lessons.map {
            Lesson(
                it.id,
                it.name,
                it.durationMinutes,
                it.description,
                it.imageLesson ?: DEFAULT_IMAGE_LESSON,
                it.videoUrl,
                it.comments?.map { commentResponse ->
                    Comment(commentResponse.id, commentResponse.text, commentResponse.userId)
                }
            )
        }

        return with(courseResponse) {
            Course(id, name, description, photoPreview, rating, lessons)
        }
    }
}