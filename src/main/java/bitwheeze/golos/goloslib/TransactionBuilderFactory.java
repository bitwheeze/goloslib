package bitwheeze.golos.goloslib;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionBuilderFactory {

    @Autowired
    private ObjectFactory<TransactionBuilder> factory;
    
    public TransactionBuilder getBuidler() {
        return factory.getObject();
    }
    
}
