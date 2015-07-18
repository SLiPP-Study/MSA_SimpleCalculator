package util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by yoon on 15. 7. 18..
 */
public class PostFixConverterTest{

    @Test
    public void 후위연산_문자열로_변환() {
        PostFixConverter converter = new PostFixConverter("3+2");
        assertEquals("32+", converter.getString());

        converter = new PostFixConverter("2*(3+2)/3");
        assertEquals("232+*3/", converter.getString());
    }

    @Test
    public void 문자열_SPLIT() {
        String value = "abcdef";

        assertEquals("a", value.split("")[0]);
    }
}