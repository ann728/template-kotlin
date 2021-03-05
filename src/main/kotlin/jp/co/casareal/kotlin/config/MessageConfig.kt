package jp.co.casareal.kotlin.config

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.core.io.ClassPathResource
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import java.io.IOException
import java.util.*

@Configuration
class MessageConfig {

    @Bean(name = ["messagesProperties"])
    @Throws(IOException::class)
    fun yamlProperties(): Properties? {
        val bean = YamlPropertiesFactoryBean()
        bean.setResources(ClassPathResource("i18n/messages.yml"))
        return bean.getObject()
    }

    @Bean
    @Throws(IOException::class)
    fun messageSource(): MessageSource? {
        val messageSource = ResourceBundleMessageSource()
        messageSource.setCommonMessages(yamlProperties())
        return messageSource
    }

    @Bean
    fun localValidatorFactoryBean(): LocalValidatorFactoryBean? {
        val localValidatorFactoryBean = LocalValidatorFactoryBean()
        localValidatorFactoryBean.setValidationMessageSource(messageSource()!!)
        return localValidatorFactoryBean
    }
}
