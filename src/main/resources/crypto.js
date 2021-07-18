/**
 * 
 */

var SecureRandom = Java.type('java.security.SecureRandom');

var window = {
    crypto: {
        getRandomValues: (buf) => {
            //print("buf_length", buf, buf.length);
            var bytes = SecureRandom.getSeed(buf.length);
            buf.set(bytes);
        }
    }
};
