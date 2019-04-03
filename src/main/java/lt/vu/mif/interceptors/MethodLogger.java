package lt.vu.mif.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Logged
public class MethodLogger {
    @AroundInvoke
    public Object logMethod(InvocationContext context) throws Exception {
        System.out.println("Called: " + context.getMethod().getName());

        return context.proceed();
    }
}
