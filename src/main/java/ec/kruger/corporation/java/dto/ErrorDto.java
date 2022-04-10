package ec.kruger.corporation.java.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author ${milton.cabrera} on 10/4/2022 12:48
 * @project app-vacunacion
 * @Version 1.0
 **/
@Data
@Builder
public class ErrorDto {

    private int codeError;
    private String messageError;
    private String uriError;
    private String timeStampError;
}
