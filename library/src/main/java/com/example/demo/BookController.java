package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Book;

@Controller
public class BookController{

	@Autowired
	private BookRepository bookrepository;
	@GetMapping("/all")
	public String getAllBooks(Model model) {
		Iterable<Book> books = bookrepository.findAll();
		model.addAttribute("books",books);
		return "booklist";

	} 

	@GetMapping("/addbook")
	public String addBook(Model model) {
		model.addAttribute("message","Enter your Book Details");
		return "bookdetails";
	}


	@PostMapping("/addbook")
	public String bookAddProcess(@RequestParam String bookname,@RequestParam String authorname,@RequestParam String email,Model model)
	{
		Book b=new Book();
		b.setBookName(bookname);
		b.setEmail(email);
		b.setAuthorName(authorname);
		bookrepository.save(b);

		model.addAttribute("message", "successfull");
		return "redirect:/all";
	}

	@GetMapping("/select/{email}")
	public String getSpecificBook(@PathVariable String email, Model model) {
		Book bookdata = bookrepository.findByEmail(email);
		model.addAttribute("bookdata",bookdata);
		return "bookDetailsByEmail";

	}


	@GetMapping("/update/{id}")

	public String updateBook(@PathVariable(value="id") Integer id,Model model) {    


		Optional<Book> optionalBookdetails = bookrepository.findById(id);
		Book bookdetails = optionalBookdetails.get();

		model.addAttribute("id",id);
		model.addAttribute("bookdetails",bookdetails);
		return "update";
	} 


	@PostMapping("/update/{id}")
	public String updateBookProcess(@PathVariable(value="id") Integer id,@RequestParam String bookname,@RequestParam String email,@RequestParam String authorname ,Model model) {

		Optional<Book> optionalBookdetails = bookrepository.findById(id);
		Book bookdetails = optionalBookdetails .get();
		bookdetails.setBookName(bookname);
		bookdetails.setEmail(email);
		bookdetails.setAuthorName(authorname);
		bookrepository.save(bookdetails);
		return "redirect:/all";
	}

	@GetMapping("/delete/{email}")
	public String deleteUser(@PathVariable String email,Model model) {  

		Book bookdetails = bookrepository.findByEmail(email);
		model.addAttribute("bookdetails",bookdetails);
		return "delete";
	}

	@PostMapping("/delete/{email}")
	public String deleteBookProcess(@RequestParam(name="email", required=false) String email,Model model) {
		System.out.println(email);
		if (email != null && !email.isEmpty()) {

			Book userdetails = bookrepository.findByEmail(email);
			bookrepository.delete(userdetails);
			return "redirect:/all";
		}
		return "redirect:/delete/"+email;
	}



	@GetMapping("/selectbyid/{id}")
	public String getSpecificBookById(@PathVariable Integer id, Model model) {
		Optional<Book> bookDetails = bookrepository.findById(id);
		Book bookData=bookDetails.get(); 
		model.addAttribute("bookData",bookData);
		return "bookDetailById";


	}


}