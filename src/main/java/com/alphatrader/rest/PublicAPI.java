package com.alphatrader.rest;

import java.lang.annotation.*;

/**
 * Interface that ensures, the api method is well documented.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
@interface PublicAPI {

}
