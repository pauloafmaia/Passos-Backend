package sys.passos.util;

import org.modelmapper.ModelMapper;

public class CopyProperties {

    private static ModelMapper modelMapper = new ModelMapper();

    public static <T> T copy (Object object, Class<T> clazz) {
        return (T) modelMapper.map(object, clazz);
    }
}
