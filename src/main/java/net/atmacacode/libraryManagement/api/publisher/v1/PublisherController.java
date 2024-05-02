package net.atmacacode.libraryManagement.api.publisher.v1;

import jakarta.validation.Valid;
import net.atmacacode.libraryManagement.business.abstracts.IPublisherService;
import net.atmacacode.libraryManagement.core.config.modelMapper.IModelMapperService;
import net.atmacacode.libraryManagement.core.result.Result;
import net.atmacacode.libraryManagement.core.result.ResultData;
import net.atmacacode.libraryManagement.core.utilies.ResultHelper;
import net.atmacacode.libraryManagement.dto.request.publisher.PublisherSaveRequest;
import net.atmacacode.libraryManagement.dto.request.publisher.PublisherUpdateRequest;
import net.atmacacode.libraryManagement.dto.response.CursorResponse;
import net.atmacacode.libraryManagement.dto.response.publisher.PublisherResponse;
import net.atmacacode.libraryManagement.entities.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/publishers")
public class PublisherController {
    private final IPublisherService publisherService;
    private final IModelMapperService modelMapper;

    public PublisherController(IPublisherService publisherService, IModelMapperService modelMapper) {
        this.publisherService = publisherService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<PublisherResponse> save(@Valid @RequestBody PublisherSaveRequest publisherSaveRequest){
        Publisher savePublisher = this.modelMapper.forRequest().map(publisherSaveRequest, Publisher.class);
        this.publisherService.save(savePublisher);
        return ResultHelper.created(this.modelMapper.forResponse().map(savePublisher, PublisherResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> getById(@PathVariable Long id){
        return ResultHelper.success(this.modelMapper.forResponse().map(this.publisherService.get(id), PublisherResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<PublisherResponse>> cursor(@RequestParam(name = "page", required = false, defaultValue = "0") int page, @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize){
        Page<Publisher> publisherPage = this.publisherService.cursor(page, pageSize);
        Page<PublisherResponse> publisherResponsePage = publisherPage.map(author -> this.modelMapper.forResponse().map(author, PublisherResponse.class));
        return ResultHelper.cursor(publisherResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> update(@Valid @RequestBody PublisherUpdateRequest publisherUpdateRequest){
        Publisher updatePublisher = this.modelMapper.forRequest().map(publisherUpdateRequest, Publisher.class);
        this.publisherService.update(updatePublisher);
        return ResultHelper.created(this.modelMapper.forResponse().map(updatePublisher, PublisherResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable Long id){
        this.publisherService.delete(id);
        return ResultHelper.ok();
    }
}
