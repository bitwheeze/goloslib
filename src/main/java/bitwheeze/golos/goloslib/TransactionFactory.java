package bitwheeze.golos.goloslib;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionFactory {

    private final ObjectFactory<TransactionBuilder> factory;
    
    public TransactionBuilder getBuidler() {
        return factory.getObject();
    }
    
}
