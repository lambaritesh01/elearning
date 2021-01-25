package repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Course;


	public interface CourseRepository extends JpaRepository<Course, Long> {
		  List<Course> findByPublished(boolean published);
		  List<Course> findByTitleContaining(String title);
		}

