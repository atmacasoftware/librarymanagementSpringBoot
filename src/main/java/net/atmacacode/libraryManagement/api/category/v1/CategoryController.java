package net.atmacacode.libraryManagement.api.category.v1;

import jakarta.validation.Valid;
import net.atmacacode.libraryManagement.business.abstracts.ICategoryService;
import net.atmacacode.libraryManagement.core.config.modelMapper.IModelMapperService;
import net.atmacacode.libraryManagement.core.result.Result;
import net.atmacacode.libraryManagement.core.result.ResultData;
import net.atmacacode.libraryManagement.core.utilies.ResultHelper;
import net.atmacacode.libraryManagement.dto.request.category.CategorySaveRequest;
import net.atmacacode.libraryManagement.dto.request.category.CategoryUpdateRequest;
import net.atmacacode.libraryManagement.dto.response.CursorResponse;

import net.atmacacode.libraryManagement.dto.response.category.CategoryResponse;
import net.atmacacode.libraryManagement.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {
    private final ICategoryService categoryService;
    private final IModelMapperService modelMapper;

    public CategoryController(ICategoryService categoryService, IModelMapperService modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CategoryResponse> save(@Valid @RequestBody CategorySaveRequest categorySaveRequest){
        Category saveCategory = this.modelMapper.forRequest().map(categorySaveRequest, Category.class);
        this.categoryService.save(saveCategory);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveCategory, CategoryResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> getById(@PathVariable Long id){
        return ResultHelper.success(this.modelMapper.forResponse().map(this.categoryService.get(id), CategoryResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CategoryResponse>> cursor(@RequestParam(name = "page", required = false, defaultValue = "0") int page, @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize){
        Page<Category> categoryPage = this.categoryService.cursor(page, pageSize);
        Page<CategoryResponse> categoryResponsePage = categoryPage.map(author -> this.modelMapper.forResponse().map(author, CategoryResponse.class));
        return ResultHelper.cursor(categoryResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> update(@Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest){
        Category updateCategory = this.modelMapper.forRequest().map(categoryUpdateRequest, Category.class);
        this.categoryService.update(updateCategory);
        return ResultHelper.created(this.modelMapper.forResponse().map(updateCategory, CategoryResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable Long id){
        this.categoryService.delete(id);
        return ResultHelper.ok();
    }
}
