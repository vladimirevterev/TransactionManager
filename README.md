# TransactionManager

TransactionManager - web-приложение, для выполнения операций со счетами пользователей.

Операции со счетами пользователей разделяются на три типа:
1. Операции перевода средств с одного счета на другой
2. Операции пополнения денежных средств на счете
3. Операции снятия денежных средств со счета



### Сборка и запуск
Для сборки приложения используется команда: ***mvn clean package***

Для запуска приложения используется команда: 

***java -jar target/transactionmanager-1.0.0-SNAPSHOT.jar***

или

***mvn spring-boot:run***

Web-приложение старует по адресу: [localhost:8080](http://localhost:8080 "Transaction Manager")



### Пользователи
В приложении реализована ролевая модель, в соответствии с которой каждый пользователь может иметь роли:
- Администратор
- Пользователь
- Системный пользователь

В сооветствии с ролями, в приложении предопределены три пользователя:
1. С правами администратора:
- login: ***admin***
2. С правами пользователя:
- login: ***user***
3. С правами системного пользователя:
- login: ***sys***

Для простоты использования для всех пользователей системы задан стандартный пароль: ***123***



### Работа с приложением
При первом обращении к приложению требуется аутентификация. В случае успешной аутентификации на главной странице отображается информация о текущем пользователе, а так же кнопки доступа к операциям приложения и консоли БД.

> Для выполнения операций приложения, то есть вызова соответствующих REST-запросов, используется Swagger-документация, в которой описаны модели передаваемых операциями данных.

Для того, чтобы перейти к Swagger-документации нужно нажать кнопку **"REST API"** на главной странице

Так же, пользователям с правами администратора доступен просмотр консоли БД. Для того, чтобы перейти к консоли БД требуется аутентифицироваться пользователем с правами администратора и на главной странице нажать кнопку **"Консоль БД"**

#### Права доступа:
1. **Системному пользователю** не доступны функции и операции приложения. Аутентификация системного пользователя запрещена.
2. **Пользователю** системы доступны основные операции со счетами пользователя, а так же операции получения информации о других пользователях системы
3. **Администратору** доступны все функции, доступные обычному пользователю, а так же открыт доступ к консоли БД и доступ к операциям манипулирования пользователями системы



### Функции приложения. Swagger-примеры
На данный момент, все операции системы разделяются на 2 группы:
1. Операции со счетами текущего пользователя:
- Перевод денежных средств со счета текущего пользователя на указанный счет
- Пополнение денежных средств текущего пользователя с опциональным указанием счета зачисления средств
- Снятие денежных средств текущего пользователя с опциональным указанием счета снятия средств
- Получение истории транзакций текущего пользователя
- Получение информации о конкретной транзакции текущего пользователя
2. Операции получения информации и манипулирования пользователями системы
- Получение списка пользователей системы
- Получение информации о текущем пользователе
- Получение информации об указанном пользователе системы
- Создание пользователя системы(доступно только администратору)
- Обновление информации о пользователе системы(доступно только администратору)
- Удаление пользователя системы(доступно только администратору)


#### Примеры операций:
>Далее будут приводиться ссылки на Swagger-документацию. Поэтому, предполагается, что на момент их открытия приложение уже запущено.

>Для получения информации об идентификаторах счетов пользователей, необходимо воспользоваться REST-запросами на получение информации о пользователях системы. 
Например, для получени списка всех пользователей системы можно выполнить запрос: [Список пользователей](http://localhost:8080/swagger-ui.html#/user-controller/getUsersUsingGET).


##### Пополнение денежных средств пользователя
1. Перейти по ссылке:
[Пополнение средств](http://localhost:8080/swagger-ui.html#/transaction-controller/replenishFundsUsingPOST)
2. Выполнить запрос с телом: 
```json
{
  "amount": 100
}
```
В результате текущему пользователю на главный счет будет начислено сто монет.
Дополнительно, в теле запроса можно указать конкретный счет текущего пользователя, на который будут начислены монеты:
```json
{
  "accountId": "<id счета текущего пользователя>",
  "amount": 1000
}
```


##### Снятие денежных средств со счета
1. Перейти по ссылке:
[Снятие средств](http://localhost:8080/swagger-ui.html#/transaction-controller/withdrawalFundsUsingPOST)
2. Выполнить запрос с телом:
```json
{
  "amount": 100
}
```
В результате с главного счета пользовтеля будет списано 100 монет.

Дополнительно, в теле запроса можно указать конкретный счет текущего пользователя, с которого будут списаны монеты:
```json
{
  "accountId": "<id счета текущего пользователя>",
  "amount": 1000
}
```

##### Перевод денежных средств на счет с идентификатором id
1. Перейти по ссылке:
[Перевод средств](http://localhost:8080/swagger-ui.html#/transaction-controller/transferFundsUsingPOST)
2. Выполнить запрос с телом:
```json
{
  "amount": 100,
  "recipientAccountId": "<id>"
}
```
В результате будет осуществлен перевод 100 монет с главного счета текущего пользователя на счет с идентификатором **id**.

Дополнительно, в теле запроса можно указать конкретный счет текущего пользователя, с которого будут списаны монеты:
```json
{
  "amount": 100,
  "recipientAccountId": "<id>",
  "sourceAccountId": "<id счета текущего пользователя>"
}
```

##### Первод денежных средств пользователя с идентификатором id
1. Перейти по ссылке:
[Перевод средств](http://localhost:8080/swagger-ui.html#/transaction-controller/transferFundsUsingPOST)
2. Выполнить запрос с телом:
```json
{
  "amount": 100,
  "recipientId": "<id>"
}
```
В результате будет осуществлен перевод 100 монет с главного счета текущего пользователя на главный счет пользователя с идентификатором **id**.

Дополнительно, в теле запроса можно указать конкретный счет текущего пользователя, с которого будут списаны монеты и конкретный счет получателя, на который будут начислены средства:
```json
{
  "amount": 100,
  "recipientId": "<id пользователя-получателя>",
  "recipientAccountId": "<id счета пользователя-получателя>",
  "sourceAccountId": "<id счета текущего пользователя>"
}
```

##### Просмотр истории транзакций
1. Перейти по ссылке:
[История транзакций](http://localhost:8080/swagger-ui.html#/transaction-controller/getUserTransactionsUsingGET)
2. Выполнить запрос
