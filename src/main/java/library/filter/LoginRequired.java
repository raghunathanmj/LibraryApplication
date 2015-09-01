package library.filter;

/**
 * Created by raghunathan.mj on 30/07/15.
 */
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//This retention annotation is required for being able to associate annotation with dateRequiredFeature in if statement
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {
}
