package ceng.ktu.computer_vision.bmp;

import java.io.IOException;
import java.util.List;

/**
 *<h1>BMP image operations</h1>
 *
 * @author almmcu
 * @version 1.0
 * @since 01.03.2017.
 */
public interface BMPImageOperations {
    List operate(int dimension) throws IOException;

}
