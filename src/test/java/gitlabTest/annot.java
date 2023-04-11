package gitlabTest;


import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(value = {ElementType.METHOD, ElementType.TYPE_USE})
public @interface annot {
    String name();
}
