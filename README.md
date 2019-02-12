### Desing Choice

I have picked MVI architecture for this app the reason behind this is using and scaling bussiness logic
with MVI architecture is really easy with just Event and corresponding Intent. MVI is architecture lets you
benefit from use of Rx and other features threading and error handling with scalable and readble code base.

I used MVI because it is the newest of current architectures that pipelines stream of events and intents into your app.

### Thrid Parties

3rd party libraries I used in this project are Retrofit, Rx, Room, OkHttp, Glide and Dagger2.

Retrofit is easy to implement and some great features for implementing Endpoint clients in android side also testing is easy.

Rx is really nice library that helps you in both boilerplate code and some threading with ease of use.

Room is kinda orm for android but not exactly to use with Sqlite dbs.

OkHttp is http engine, been using it since android had bugs with apapche in older versions and it has better or faster imp of buffers in networking op also supports logging

and interceptions for scaling your app further.

Glide is image loading lib that is one of the best in my opinion and it also can scale it greatly in extensions and caching also manipulation iamges both in memory or io aka disk

Dagger2 is one of my must have libs in android app dev since it make app code clean in terms of di (Dependency Injection) practices and lets you manage your memory allocation optiomal
and also you can get app development further if you are followin SOLID princaples with this di.

### Extra to be done

Would love to put some setting to change search of new city in the app and may be flicker api that would let you find background photos of selected city to show user

Also would like to add jenkins for automated builds and etc.

Also better ui since been hurry can not take more time to code better way

