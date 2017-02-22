import health.TemplateHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.HelloWorldResource;

/**
 * Created by julian on 2/21/17.
 */
public class HelloWorldApplication extends Application<HelloWorldConfiguration>{
    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName(){
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment){
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(), configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);

    }
}
