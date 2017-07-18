<html xmlns:th="http://www.thymeleaf.org" xmlns:tiles="http://www.thymeleaf.org">
  <head>
    <title tiles:fragment="title">Messages : Create</title>
  </head>
  <body>
    <div tiles:fragment="content">
        <form name="f" th:action="@{/login}" method="post">      
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>         
            <fieldset>
                <legend>Please Login</legend>
                <div th:if="${param.logout}" class="alert alert-success"> 
                    You have been logged out.<br/><br/>  
                  <strong>login:</strong>  dima_pup@mail.ru<br/>
                 <strong>password:</strong> 123<br/>
                  <strong>roles:</strong> RESGISTERED_USER,BOOKING_MANAGER<br/><br/>
                  <strong>login:</strong>  vasya@mail.ru<br/>
                  <strong>password:</strong> 123<br/>
                  <strong>roles:</strong> RESGISTERED_USER<br/>
                </div>
                <label for="username">Username</label>
                <input type="text" id="username" name="username"/>        
                <label for="password">Password</label>
                <input type="password" id="password" name="password"/>
                
                Remember Me:<input type='checkbox' name="remember-me-param"/>
                
                <div class="form-actions">
                    <button type="submit" class="btn">Log in</button>
                </div>
            </fieldset>
        </form>
    </div>
  </body>
</html>