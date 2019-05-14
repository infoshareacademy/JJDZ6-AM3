package config;

import errors.ValidationError;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Provider
public class ConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        List<ValidationError> messages = e.getConstraintViolations().stream()
                .map(this::toValidationError)
                .collect(Collectors.toList());

        return Response.status(422).type(MediaType.APPLICATION_JSON).entity(messages).build();
    }

    private ValidationError toValidationError(ConstraintViolation constraintViolation) {
        Path propertyPath = constraintViolation.getPropertyPath();
        Optional<Path.Node> leafNodeOptional =
                StreamSupport.stream(propertyPath.spliterator(), false).reduce((a, b) -> b);
        String name = leafNodeOptional.isPresent() ? leafNodeOptional.get().getName() : "Unknown field";
        ValidationError error = new ValidationError();
        error.setField(name);
        error.setMessage(constraintViolation.getMessage());
        return error;
    }

}
