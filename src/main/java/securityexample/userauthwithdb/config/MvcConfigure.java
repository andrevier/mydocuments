package securityexample.userauthwithdb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfigure implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/home").setViewName("home");
        //registry.addViewController("/").setViewName("home");
        registry.addViewController("/login").setViewName("login");
        //registry.addViewController("/login?logout").setViewName("login");
        //registry.addViewController("/logout").setViewName("login");
        //registry.addViewController("/edit").setViewName("edit");
        // registry.addViewController("/update-document").setViewName("home");
        // registry.addViewController("/create-document").setViewName("home");
    }

}
