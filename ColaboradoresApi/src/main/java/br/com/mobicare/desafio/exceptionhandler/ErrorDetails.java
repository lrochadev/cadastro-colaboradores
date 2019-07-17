package br.com.mobicare.desafio.exceptionhandler;

/**
 * 
 * @author Leonardo de Souza Rocha
 *
 */
public class ErrorDetails {
    
	private String title;
	private int status;
	private String detail;
	private String timestamp;
	private String developerMessage;
	private String uuid;
	
	public ErrorDetails() {}
	
	public ErrorDetails(String title) {
		super();
		this.title = title;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
    
    public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public static final class Builder {
        
    	private String title;
        
    	private int status;
        
    	private String detail;
        
    	private String timestamp;
        
    	private String developerMessage;
    	
        private Builder() {}

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder timestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public ErrorDetails build() {
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setTitle(title);
            errorDetails.setStatus(status);
            errorDetails.setDetail(detail);
            errorDetails.setTimestamp(timestamp);
            errorDetails.setDeveloperMessage(developerMessage);
            return errorDetails;
        }
    }

	@Override
	public String toString() {
		return "ErrorDetails [title=" + title + ", status=" + status + ", detail=" + detail + ", timestamp=" + timestamp + ", developerMessage=" + developerMessage + ", uuid=" + uuid + "]";
	}
}