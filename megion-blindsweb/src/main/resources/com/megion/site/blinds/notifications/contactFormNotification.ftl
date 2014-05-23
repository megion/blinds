
<style type="text/css">
    .messageBody {font-size: small;color: #333333;}
</style>

<div class="messageBody">
 <dl>
        <dt><b>Имя, фамилия</b></dt>
        <dd>${notification.name}</dd>
        
        <dt><b>Email</b></dt>
        <dd><a href="mailto:${notification.email}">${notification.email}</a></dd>
        
        <dt><b>Тема</b></dt>
        <dd>${notification.messageTitle}</dd>
        
        <dt><b>Запрос</b></dt>
        <dd>${notification.message}</dd>      
  </dl>
 </div>
