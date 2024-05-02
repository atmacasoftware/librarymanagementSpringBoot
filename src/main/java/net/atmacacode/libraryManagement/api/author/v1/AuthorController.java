package net.atmacacode.libraryManagement.api.author.v1;

import jakarta.validation.Valid;
import net.atmacacode.libraryManagement.business.abstracts.IAuthorService;
import net.atmacacode.libraryManagement.core.config.modelMapper.IModelMapperService;
import net.atmacacode.libraryManagement.core.result.Result;
import net.atmacacode.libraryManagement.core.result.ResultData;
import net.atmacacode.libraryManagement.core.utilies.ResultHelper;
import net.atmacacode.libraryManagement.dto.request.author.AuthorSaveRequest;
import net.atmacacode.libraryManagement.dto.request.author.AuthorUpdateRequest;
import net.atmacacode.libraryManagement.dto.response.CursorResponse;
import net.atmacacode.libraryManagement.dto.response.author.AuthorResponse;
import net.atmacacode.libraryManagement.entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {
    private final IAuthorService authorService;
    private final IModelMapperService modelMapper;

    public AuthorController(IAuthorService authorService, IModelMapperService modelMapper) {
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AuthorResponse> save(@Valid @RequestBody AuthorSaveRequest authorSaveRequest){
        Author saveAuthor = this.modelMapper.forRequest().map(authorSaveRequest, Author.class);
        this.authorService.save(saveAuthor);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAuthor, AuthorResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> getById(@PathVariable Long id){
        return ResultHelper.success(this.modelMapper.forResponse().map(this.authorService.get(id), AuthorResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AuthorResponse>> cursor(@RequestParam(name = "page", required = false, defaultValue = "0") int page, @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize){
        Page<Author> authorPage = this.authorService.cursor(page, pageSize);
        Page<AuthorResponse> authorResponsePage = authorPage.map(author -> this.modelMapper.forResponse().map(author, AuthorResponse.class));
        return ResultHelper.cursor(authorResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> update(@Valid @RequestBody AuthorUpdateRequest authorUpdateRequest){
        Author updateAuthor = this.modelMapper.forRequest().map(authorUpdateRequest, Author.class);
        this.authorService.update(updateAuthor);
        return ResultHelper.created(this.modelMapper.forResponse().map(updateAuthor, AuthorResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable Long id){
        this.authorService.delete(id);
        return ResultHelper.ok();
    }
}
