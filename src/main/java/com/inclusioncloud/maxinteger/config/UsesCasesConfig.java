package com.inclusioncloud.maxinteger.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * Configuration class for Use Cases in the application.
 * This class uses Spring's {@link ComponentScan} to specify which packages to scan for Spring beans.
 * Specifically, it is configured to scan the 'application.usecase' package for components that adhere to the Use Case naming convention.
 *
 * <p>This approach allows for a more modular and organized structure, ensuring that only the necessary components
 * related to the use cases are registered as Spring beans.</p>
 *
 *
 * where only classes named with 'UseCase' suffix are considered for dependency injection.</p>
 */
@Configuration
@ComponentScan(basePackages = "com.inclusioncloud.maxinteger",
        includeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")},
        useDefaultFilters = false)
public class UsesCasesConfig {
    // This class does not require additional methods or attributes as its purpose is solely configuration.
}
