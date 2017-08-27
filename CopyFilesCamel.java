package com.codecamel.file;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;


/**
 * Created by yifan on 2017-08-27.
 */
public class CopyFilesCamel {
    public static void main(String[] args) {
        // step 1: Define the Camel Context
        CamelContext context = new DefaultCamelContext();

        try {

            // step 2: addRoute()
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("file:/Users/yifan/Desktop/Java/input?noop=true")
                        //Note: Besides adding dependencies, to enable logging we use one of the bellow two methods: .to() or .log() in the route.
                            .to("log:?level=INFO&showBody=true&showHeaders=true")
                            //.log("Received Message is ${body} and Headers are ${headers}")
                            .to("file:/Users/yifan/Desktop/Java/output");
                }
            });
            //step 3: start the route
            context.start();
            Thread.sleep(2000);
            context.stop();

        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Inside Exception : " + e);
        }


    }
}
