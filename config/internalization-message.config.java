@Configuration
@EnableWebMvc
@ComponentScan
public class MvcWebConfig  extends WebMvcConfigurerAdapter {

        static final Logger logger = LoggerFactory.getLogger(MvcWebConfig.class);

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(localeChangeInterceptor());
        }

        @Bean
        public LocaleChangeInterceptor localeChangeInterceptor() {
                LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
                localeChangeInterceptor.setParamName("language");
                return localeChangeInterceptor;
        }

        @Bean(name = "localeResolver")
        public CookieLocaleResolver localeResolver() {
                CookieLocaleResolver localeResolver = new CookieLocaleResolver();
                Locale defaultLocale = new Locale("en");
                localeResolver.setDefaultLocale(defaultLocale);
                return localeResolver;
        }

        @Bean
        public ReloadableResourceBundleMessageSource messageSource() {
                ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
                messageSource.setBasename("classpath:lang/messages");
                messageSource.setCacheSeconds(10); //reload messages every 10 seconds
                return messageSource;
        }
        
// as alternative with-out automatic reloading, you could use the following:
//        @Bean
//        public ResourceBundleMessageSource messageSource() {
//                ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//                messageSource.setBasename("lang/messages");
//                return messageSource;
//        }
}