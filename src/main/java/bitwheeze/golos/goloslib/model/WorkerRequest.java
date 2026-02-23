package bitwheeze.golos.goloslib.model;

import tools.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;

@Data
@JsonDeserialize(using = WorkerRequestDeserializer.class)
public class WorkerRequest {
	private String id;
	private long volume; //TODO: wtf?
}
