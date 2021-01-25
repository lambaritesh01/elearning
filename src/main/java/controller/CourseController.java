package controller;

import java.util.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import antlr.collections.*;
import model.Course;
import repository.CourseRepository;

@RestController
@RequestMapping(path="/api")
public class CourseController {

	public CourseController() {		System.out.println("/n/n/n/nbc");
}
  @Autowired
  CourseRepository courseRepository;

  @RequestMapping("/courses") 
  public ResponseEntity<List<Course>> getAllTutorials(@RequestParam(required = false) String title) {
    try {
    	List<Course> courses = new ArrayList<Course>();
    	if(title==null)
    		courseRepository.findAll().forEach(courses::add);
    	else
    		courseRepository.findByTitleContaining(title).forEach(courses::add);
    	if(courses.isEmpty())
    	{
    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	}
		return new ResponseEntity<>(courses,HttpStatus.OK);
		 	
    }
    catch(Exception e) {
    	return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    	
    }
  }

  @GetMapping(path="/courses/{id}")
  public ResponseEntity<Course> getTutorialById(@PathVariable("id") long id) {
   Optional<Course> courseData = courseRepository.findById(id);
   if(courseData.isPresent())
	   return new ResponseEntity<>(courseData.get(),HttpStatus.OK);
   else
	   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   
  }

  @RequestMapping(value="/courses",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
		  ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Course> createTutorial(@RequestBody Course course) {
    System.out.println(course.toString());
	  try {
		  Course _course = courseRepository.save(new Course(course.getTitle(),course.getDescription(),false));
		  return new ResponseEntity<>(_course,HttpStatus.CREATED);
	  }
	   catch(Exception e) {
	    	return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	    	
	    }
  }
  @PutMapping(path="/courses/{id}")
  public ResponseEntity<Course> updateTutorial(@PathVariable("id") long id, @RequestBody Course course) {
	  Optional<Course> courseData = courseRepository.findById(id);
	  
	  if(courseData.isPresent()) {
		  Course _course = courseData.get();
		  _course.setTitle(course.getTitle());
		  _course.setDescription(course.getDescription());
		  _course.setPublished(course.isPublished());
		  return new ResponseEntity<>(courseRepository.save(_course),HttpStatus.OK); 
	  }
	   else
		   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping(path="/courses/{id}")
  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
	  try {
		  courseRepository.deleteById(id);
		  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }
	  catch (Exception e)
	  {
		  return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	  }
  }

  @DeleteMapping(path="/courses")
  public ResponseEntity<HttpStatus> deleteAllTutorials() {
	  try {
		  courseRepository.deleteAll();
		  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }
	  catch (Exception e)
	  {
		  return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	  }
  }

  @GetMapping(path="/courses/published")
  public ResponseEntity<List<Course>> findByPublished() {
	  try {
		  List<Course> courses = courseRepository.findByPublished(true);
		  if(courses.isEmpty())
		  {
			  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  }
		  return new ResponseEntity<>(courses,HttpStatus.OK);
	  }
	  catch(Exception e) {
		  return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	  }
  
  }
}