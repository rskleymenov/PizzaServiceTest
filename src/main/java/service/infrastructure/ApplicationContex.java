package service.infrastructure;

public interface ApplicationContex {
	Object getBean(String bean) throws Exception;
}