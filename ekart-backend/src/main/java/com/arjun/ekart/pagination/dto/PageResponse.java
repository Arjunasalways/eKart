package com.arjun.ekart.pagination.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PageResponse<T> {

	    private List<T> content;

	    private int page;
	    private int size;

	    private long totalElements;
	    private int totalPages;
	    private int numberOfElements;

	    private boolean first;
	    private boolean last;
	    
	    
	    
	    
		public int getNumberOfElements() {
			return numberOfElements;
		}

		public void setNumberOfElements(int numberOfElements) {
			this.numberOfElements = numberOfElements;
		}

		public List<T> getContent() {
			return content;
		}
		
		public void setContent(List<T> content) {
			this.content = content;
		}
		public int getPage() {
			return page;
		}
		public void setPage(int page) {
			this.page = page;
		}
		public int getSize() {
			return size;
		}
		public void setSize(int size) {
			this.size = size;
		}
		public long getTotalElements() {
			return totalElements;
		}
		public void setTotalElements(long totalElements) {
			this.totalElements = totalElements;
		}
		public int getTotalPages() {
			return totalPages;
		}
		public void setTotalPages(int totalPages) {
			this.totalPages = totalPages;
		}
		public boolean isFirst() {
			return first;
		}
		public void setFirst(boolean first) {
			this.first = first;
		}
		public boolean isLast() {
			return last;
		}
		public void setLast(boolean last) {
			this.last = last;
		}
	    
	    
}
