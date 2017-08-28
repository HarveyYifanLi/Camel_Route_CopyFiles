package com.codecamel.file;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;


/**
 * Created by yifan on 2017-08-27.
 */
public class CopyFilesCamelMultiRoutes {
    public static void main(String[] args) {
        // step 1: Define the Camel Context
        CamelContext context = new DefaultCamelContext();

        try {

            // step 2: addRoute()
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    //we create two routes:

                    //note: this route put files in input dir to two dirs: output and output1
                    from("file:/Users/yifan/Desktop/input?noop=true")

                            .to("log:?level=INFO&showBody=true&showHeaders=true")
                            //.log("Received Message is ${body} and Headers are ${headers}")
                            .to("file:/Users/yifan/Desktop/output1")
                            .to("file:/Users/yifan/Desktop/output");

                    //note: this new route put files in another_input dir to another_output dir
                    from("file:/Users/yifan/Desktop/another_input?noop=true")
                            .to("log:?level=INFO&showBody=true&showHeaders=true")
                            .to("file:/Users/yifan/Desktop/another_output");


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
