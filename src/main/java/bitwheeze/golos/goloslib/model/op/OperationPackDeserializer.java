package bitwheeze.golos.goloslib.model.op;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OperationPackDeserializer extends StdDeserializer<OperationPack> {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public OperationPackDeserializer() {
        this(null);
    }
  
    public OperationPackDeserializer(Class<OperationPack> t) {
        super(t);
    }

    @Override
    public OperationPack deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        log.debug("parse Operation");
        
        JsonNode node = p.readValueAsTree();
        if(!node.isArray()) {
            throw new RuntimeException("Operation as an array expected " + node.toString());
        }
        if(node.size() != 2) {
            throw new RuntimeException("Operation as an array of 2 elements (String, Object) expected, actual " + node.size());
        }
        
        String opName = node.get(0).asText();
        JsonNode opBodyRaw = node.get(1);
        
        Operation op = getOperation(p, ctxt, opName, opBodyRaw);
        
        return new OperationPack(opName, op);
    }

    @SneakyThrows
    private Operation getOperation(JsonParser p, DeserializationContext ctxt, String opName, JsonNode opBodyRaw) {
        
        Optional<Class<?>> beanClass = getOpClass(opName);
        if(beanClass.isPresent()) {
            return (Operation) p.getCodec().treeToValue(opBodyRaw, beanClass.get());
        } else {
            RawOperation op = new RawOperation();
            op.setOpName(opName);
            op.setOpBody(opBodyRaw.toString());
            log.warn("unknwon operation {}", op);
            return op;
        }
    }
    
    Optional<Class<?>> getOpClass(String opName) {
        var scanner = new ClassPathScanningCandidateComponentProvider(true);
        scanner.addIncludeFilter(new AnnotationTypeFilter(OpName.class));
        
        for (BeanDefinition bd : scanner.findCandidateComponents(Operation.class.getPackageName())) {
            try {
                Class<?> candidate = Operation.class.getClassLoader().loadClass(bd.getBeanClassName());
                OpName a = (OpName)candidate.getAnnotation(OpName.class);
                if(opName.equals(a.value())) {
                    return Optional.ofNullable(candidate);
                }
            } catch (ClassNotFoundException e) {
            }
        }
        return Optional.empty();
    }
    
  
}
