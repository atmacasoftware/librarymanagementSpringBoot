package net.atmacacode.libraryManagement.core.config.modelMapper;
import org.modelmapper.ModelMapper;


public interface IModelMapperService {
    ModelMapper forRequest();
    ModelMapper forResponse();
}
