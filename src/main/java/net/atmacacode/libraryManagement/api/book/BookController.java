package net.atmacacode.libraryManagement.api.book;

import jakarta.validation.Valid;
import net.atmacacode.libraryManagement.business.abstracts.IBookService;
import net.atmacacode.libraryManagement.core.config.modelMapper.IModelMapperService;
import net.atmacacode.libraryManagement.core.result.Result;
import net.atmacacode.libraryManagement.core.result.ResultData;
import net.atmacacode.libraryManagement.core.utilies.ResultHelper;
import net.atmacacode.libraryManagement.dto.request.book.BookSaveRequest;
import net.atmacacode.libraryManagement.dto.request.book.BookUpdateRequest;
import net.atmacacode.libraryManagement.dto.response.CursorResponse;
import net.atmacacode.libraryManagement.dto.response.book.BookResponse;
import net.atmacacode.libraryManagement.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/books")
public class BookController {
    private final IBookService bookService;
    private final IModelMapperService modelMapper;

    public BookController(IBookService bookService, IModelMapperService modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookResponse> save(@Valid @RequestBody BookSaveRequest bookSaveRequest){
        Book saveBook = this.modelMapper.forRequest().map(bookSaveRequest, Book.class);
        this.bookService.save(saveBook);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveBook, BookResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> getById(@PathVariable Long id){
        return ResultHelper.success(this.modelMapper.forResponse().map(this.bookService.get(id), BookResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookResponse>> cursor(@RequestParam(name = "page", required = false, defaultValue = "0") int page, @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize){
        Page<Book> categoryPage = this.bookService.cursor(page, pageSize);
        Page<BookResponse> categoryResponsePage = categoryPage.map(author -> this.modelMapper.forResponse().map(author, BookResponse.class));
        return ResultHelper.cursor(categoryResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> update(@Valid @RequestBody BookUpdateRequest bookUpdateRequest){
        Book updateBook = this.modelMapper.forRequest().map(bookUpdateRequest, Book.class);
        this.bookService.update(updateBook);
        return ResultHelper.created(this.modelMapper.forResponse().map(updateBook, BookResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable Long id){
        this.bookService.delete(id);
        return ResultHelper.ok();
    }

}
