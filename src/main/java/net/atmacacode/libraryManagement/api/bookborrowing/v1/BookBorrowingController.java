package net.atmacacode.libraryManagement.api.bookborrowing.v1;

import jakarta.validation.Valid;
import net.atmacacode.libraryManagement.business.abstracts.IBookBorrowingService;
import net.atmacacode.libraryManagement.core.config.modelMapper.IModelMapperService;
import net.atmacacode.libraryManagement.core.result.Result;
import net.atmacacode.libraryManagement.core.result.ResultData;
import net.atmacacode.libraryManagement.core.utilies.ResultHelper;
import net.atmacacode.libraryManagement.dto.request.bookborrowing.BookBorrowingSaveRequest;
import net.atmacacode.libraryManagement.dto.request.bookborrowing.BookBorrowingUpdateRequest;
import net.atmacacode.libraryManagement.dto.response.CursorResponse;
import net.atmacacode.libraryManagement.dto.response.bookborrowing.BookBorrowingResponse;
import net.atmacacode.libraryManagement.entities.BookBorrowing;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/book-borrowing")
public class BookBorrowingController {
    private final IBookBorrowingService bookBorrowingService;
    private final IModelMapperService modelMapper;

    public BookBorrowingController(IBookBorrowingService bookBorrowingService, IModelMapperService modelMapper) {
        this.bookBorrowingService = bookBorrowingService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookBorrowingResponse> save(@Valid @RequestBody BookBorrowingSaveRequest bookBorrowingSaveRequest){
        BookBorrowing saveBook = this.modelMapper.forRequest().map(bookBorrowingSaveRequest, BookBorrowing.class);
        this.bookBorrowingService.save(saveBook);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveBook, BookBorrowingResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowingResponse> getById(@PathVariable Long id){
        return ResultHelper.success(this.modelMapper.forResponse().map(this.bookBorrowingService.get(id), BookBorrowingResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookBorrowingResponse>> cursor(@RequestParam(name = "page", required = false, defaultValue = "0") int page, @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize){
        Page<BookBorrowing> bookBorrowingPagePage = this.bookBorrowingService.cursor(page, pageSize);
        Page<BookBorrowingResponse> bookBorrowingResponsePage = bookBorrowingPagePage.map(book_borrowing -> this.modelMapper.forResponse().map(book_borrowing, BookBorrowingResponse.class));
        return ResultHelper.cursor(bookBorrowingResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowingResponse> update(@Valid @RequestBody BookBorrowingUpdateRequest bookBorrowingUpdateRequest){
        BookBorrowing updateBookBorrowing = this.modelMapper.forRequest().map(bookBorrowingUpdateRequest, BookBorrowing.class);
        this.bookBorrowingService.update(updateBookBorrowing);
        return ResultHelper.created(this.modelMapper.forResponse().map(updateBookBorrowing, BookBorrowingResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable Long id){
        this.bookBorrowingService.delete(id);
        return ResultHelper.ok();
    }

}