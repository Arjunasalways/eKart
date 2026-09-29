package com.arjun.ekart.pagination.mapper;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.arjun.ekart.pagination.dto.PageResponse;

@Component
public class PageMapper {
	
	public <T>PageResponse<T> toResponse(Page<T> page){
	
		PageResponse<T> pageResponse = new PageResponse<>();
		
		pageResponse.setContent(page.getContent());
		pageResponse.setPage(page.getNumber());
		pageResponse.setSize(page.getSize());
		pageResponse.setTotalElements(page.getTotalElements());
		pageResponse.setTotalPages(page.getTotalPages());
		pageResponse.setNumberOfElements(page.getNumberOfElements());
		pageResponse.setFirst(page.isFirst());
		pageResponse.setLast(page.isLast());
	
		return pageResponse;
	}
}
