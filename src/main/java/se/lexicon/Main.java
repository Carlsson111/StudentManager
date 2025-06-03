package se.lexicon;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import se.lexicon.Config.ComponentScanConfig;
import se.lexicon.data_access.StudentDao;
import se.lexicon.util.UserInputService;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        UserInputService userInputService = context.getBean(UserInputService.class);
    }
}