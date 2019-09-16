Project code is organized in `Clean Architecture` manner. 
There are 3 layers: `Domain`, `Data`, `Presentation`. The most inner layer is `Domain` which contains business logic. This layer isn't aware of existence of other 2 layers, it only defines `models`, `GetCv` use case and `CvRepository` interface. 
`Data` layer implements `CvRepository` and here is defined how cv is fetched. In this case I used simple mock data source (from gist json) which could be easily replaced with any other data source - remote sheet, remote api API or whatever.  
Last layer is `Presentation` layer. `Presentation` layer is organized with `MVVM` pattern and `Databinding`. I have choose `MVVM` pattern because it plays nicely with `Android Lifecycle` (`LiveData`, `ViewModels` are restored when screen configuration changes) and `databinding` concept. `ViewModels` expose view-related observable fields (`LiveData`). Views just simply observe those fields and apply changes when needed.

Those layers are divided into modules which ensure the following: 
- `Domain` (business logic) layer knows nothing about other layers
- `Data` layer knows only about domain
- `Presentation` as the most outer layer knows about other layers

Each layers has their own models which could fulfill different purposes, eg. `CvResponse` in `Data` layer could have slightly different structure then business logic's `Cv` model.

I have used `RxJava`/`RxAndroid` in order to support reactive streams between those layers. `Domain` `UseCases` are built on top of reactive streams. Rx method chaining helps when it comes to map between models. `RxJava` also provides easily thread management. 

I have used `Dagger 2` as dependency injection tool. This way we can easily inject use cases and change implementations of abstractions when needed. 

I have used `threetenabp` library for date management as it provides same api as `Java 8 Time` in pre 24 android api which in my opinion is much easier to work with.

In few places I have used `Factory` pattern eg. `CertificateRow.Factory` as it helps with object creation using dependency injection. When you need to create `CertificateRow` you would need to provide `dateFormatter` dependency. `Factory` take care of this.