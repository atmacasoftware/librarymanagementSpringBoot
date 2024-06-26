# Spring Boot ile Kütüphane Yönetim Sistemi

Kütüphane sisteminde aşağıdaki entity’ler bulunmaktadır.

* Kitap : Kitapların özelliklerini içeren entity/tablo.
* Yazar : Yazarların özelliklerini içeren entity/tablo. 
* Kategori : Kitap kategorilerini içeren entity/tablo. 
* Yayın Evi : Yayınevlerinin özelliklerini içeren entity/tablo. 
* Kitap Ödünç Alınması : Kitap ödünç alma işlemlerini izleyen entity/tablo. Bu tablo, ödünç alınan kitabın kim tarafından alındığını, alınma tarihini ve teslim tarihini içerecektir.

Her bir varlık için temel değişkenler aşağıdaki gibidir.

### Kitap (Book):
* id (Benzersiz kitap kimliği)
* name(Kitap adı)
* publicationYear (Yayın yılı)
* stock (Kütüphanedeki miktarı)

### Yazar (Author):

* id (Benzersiz yazar kimliği)
* name (Yazarın adı)
* birthDate (Yazarın doğum yılı)
* country (Yazarın ülkesi)

### Kategoriler (Category):
* id (Benzersiz kategori kimliği) 
* name (Kategori adı)
* description (Kategori tanımı)

### Yayınevi (Publisher)
* id (Benzersiz kimliği)
* name (Yayınevi ismi)
* establishmentYear (Kuruluş yılı)
* address (Yayınevi adresi)

### Kitap Ödünç Alma (BookBorrowing)

* id (Benzersiz kimliği)
* borrowerName (Kitap ödünç alan kişi adı soyadı)
* borrowerEmail (Kitap ödünç alan kişi mail adresi)
* borrowingDate (Kitap ödünç alma tarihi)
* returnDate (Kitabın teslim edildiği tarih, ilk kayıtta null olacak. Kitap teslim edilince tarih güncellenecek)

### Tablolar Arasındaki İlişkiler

* Bir kitabın bir yazarı olabilir, bir yazarın birden fazla kitabı olabilir. (One-to-Many ilişkisi).
* Bir kategori birden fazla kitaba sahip olabilirken, bir kitap birden fazla kategoriye ait olabilir. (Many-to-Many ilişkisi).
* Bir kitabın bir yayınevi olabilir, bir yayınevinin birden fazla kitabı olabilir. (One-to-Many ilişkisi).
* Bir kitap birden fazla ödünç alma işlemine sahip olabilir, ancak her ödünç alma işlemi yalnızca bir kitaba ait olabilir. (One-to-Many ilişkisi).

### End Pointler

### Category

#### POST save
* http://localhost:8080/v1/categories

#### GET get
* http://localhost:8080/v1/categories/6

#### GET cursor
* http://localhost:8080/v1/categories

#### PUT update
* http://localhost:8080/v1/categories

#### DELETE delete
* http://localhost:8080/v1/categories/6

<hr>

### Author

#### POST save
* http://localhost:8080/v1/authors

#### GET get
* http://localhost:8080/v1/authors/2

#### GET cursor
* http://localhost:8080/v1/authors

#### PUT update
* http://localhost:8080/v1/authors

#### DELETE delete
* http://localhost:8080/v1/authors/3

<hr>

### Publisher

#### POST save
* http://localhost:8080/v1/publishers

#### GET get
* http://localhost:8080/v1/publishers/2

#### GET cursor
* http://localhost:8080/v1/publishers

#### PUT update
* http://localhost:8080/v1/publishers

#### DELETE delete
* http://localhost:8080/v1/publishers/3

<hr>

### Book

#### POST save
* http://localhost:8080/v1/books

#### GET get
* http://localhost:8080/v1/books/1

#### GET cursor
* http://localhost:8080/v1/books

#### PUT update
* http://localhost:8080/v1/books

#### DELETE delete
* http://localhost:8080/v1/books/1

### BookBorrowing

#### POST save
* http://localhost:8080/v1/book-borrowing

#### GET get
* http://localhost:8080/v1/book-borrowing/1

#### GET cursor
* http://localhost:8080/v1/book-borrowing

#### PUT update
* http://localhost:8080/v1/book-borrowing

#### DELETE delete
* http://localhost:8080/v1/book-borrowing/1