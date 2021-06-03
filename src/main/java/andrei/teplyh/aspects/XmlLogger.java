package andrei.teplyh.aspects;

import andrei.teplyh.annotations.LogXML;
import andrei.teplyh.entities.accounts.User;
import andrei.teplyh.repositories.LoggerRepository;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Aspect
@Component
public class XmlLogger {
    private final LoggerRepository loggerRepository;

    @Value("${user.data.file}")
    private String filePath;

    @Autowired
    public XmlLogger(LoggerRepository loggerRepository) {
        this.loggerRepository = loggerRepository;
    }

    @Pointcut("@annotation(logXML)")
    public void log(LogXML logXML) {}

    @AfterReturning(pointcut = "log(logXML)", returning = "retVal")
    public void afterReturning(Object retVal, LogXML logXML) {
        loggerRepository.log(filePath, (User) retVal);
    }
}
