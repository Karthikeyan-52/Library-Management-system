// ==========================================
// BOOK API URL
// ==========================================

const API_URL = "/api/books";


// ==========================================
// LOAD ALL BOOKS
// ==========================================

function loadBooks() {

    fetch(API_URL)
        .then(response => response.json())
        .then(books => {

            displayBooks(books);

        })
        .catch(error => {

            console.error("Error loading books:", error);

            alert("Unable to load books");

        });
}


// ==========================================
// DISPLAY BOOKS IN TABLE
// ==========================================

function displayBooks(books) {

    const tableBody =
        document.getElementById("bookTableBody");

    tableBody.innerHTML = "";


    books.forEach(book => {

        const row =
            document.createElement("tr");

        row.innerHTML = `

            <td>${book.bookId}</td>

            <td>${book.title}</td>

            <td>${book.author}</td>

            <td>${book.isbn}</td>

            <td>${book.category || ""}</td>

            <td>${book.quantity}</td>

            <td>${book.availableQuantity}</td>

            <td>

                <button
                    class="btn-warning"
                    onclick="editBook(${book.bookId})">

                    Edit

                </button>


                <button
                    class="btn-danger"
                    onclick="deleteBook(${book.bookId})">

                    Delete

                </button>

            </td>

        `;

        tableBody.appendChild(row);

    });

}


// ==========================================
// ADD NEW BOOK
// ==========================================

function addBook() {

    const book = {

        title:
            document.getElementById("title").value,

        author:
            document.getElementById("author").value,

        isbn:
            document.getElementById("isbn").value,

        category:
            document.getElementById("category").value,

        quantity:
            parseInt(
                document.getElementById("quantity").value
            ),

        availableQuantity:
            parseInt(
                document.getElementById("quantity").value
            )

    };


    fetch(API_URL, {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(book)

    })

    .then(response => {

        if (!response.ok) {

            throw new Error(
                "Failed to add book"
            );

        }

        return response.json();

    })

    .then(data => {

        alert("Book added successfully!");

        clearForm();

        loadBooks();

    })

    .catch(error => {

        console.error(error);

        alert("Error adding book");

    });

}


// ==========================================
// DELETE BOOK
// ==========================================

function deleteBook(id) {

    const confirmDelete =
        confirm(
            "Are you sure you want to delete this book?"
        );


    if (!confirmDelete) {

        return;

    }


    fetch(`${API_URL}/${id}`, {

        method: "DELETE"

    })

    .then(response => {

        if (!response.ok) {

            throw new Error(
                "Failed to delete book"
            );

        }

        return response.text();

    })

    .then(message => {

        alert(message);

        loadBooks();

    })

    .catch(error => {

        console.error(error);

        alert("Error deleting book");

    });

}


// ==========================================
// EDIT BOOK
// ==========================================

function editBook(id) {

    fetch(`${API_URL}/${id}`)

        .then(response =>
            response.json()
        )

        .then(book => {

            document.getElementById("bookId").value =
                book.bookId;

            document.getElementById("title").value =
                book.title;

            document.getElementById("author").value =
                book.author;

            document.getElementById("isbn").value =
                book.isbn;

            document.getElementById("category").value =
                book.category;

            document.getElementById("quantity").value =
                book.quantity;


            document.getElementById("addButton").style.display =
                "none";

            document.getElementById("updateButton").style.display =
                "inline-block";

        })

        .catch(error => {

            console.error(error);

            alert("Unable to load book");

        });

}


// ==========================================
// UPDATE BOOK
// ==========================================

function updateBook() {

    const id =
        document.getElementById("bookId").value;


    const book = {

        title:
            document.getElementById("title").value,

        author:
            document.getElementById("author").value,

        isbn:
            document.getElementById("isbn").value,

        category:
            document.getElementById("category").value,

        quantity:
            parseInt(
                document.getElementById("quantity").value
            ),

        availableQuantity:
            parseInt(
                document.getElementById("quantity").value
            )

    };


    fetch(`${API_URL}/${id}`, {

        method: "PUT",

        headers: {

            "Content-Type":
                "application/json"

        },

        body:
            JSON.stringify(book)

    })

    .then(response => {

        if (!response.ok) {

            throw new Error(
                "Failed to update book"
            );

        }

        return response.json();

    })

    .then(data => {

        alert(
            "Book updated successfully!"
        );

        clearForm();

        loadBooks();

    })

    .catch(error => {

        console.error(error);

        alert(
            "Error updating book"
        );

    });

}


// ==========================================
// SEARCH BOOKS
// ==========================================

function searchBooks() {

    const keyword =
        document.getElementById(
            "searchInput"
        ).value;


    if (keyword.trim() === "") {

        loadBooks();

        return;

    }


    fetch(
        `${API_URL}/search?keyword=${encodeURIComponent(keyword)}`
    )

        .then(response =>
            response.json()
        )

        .then(books => {

            displayBooks(books);

        })

        .catch(error => {

            console.error(error);

            alert(
                "Error searching books"
            );

        });

}


// ==========================================
// CLEAR FORM
// ==========================================

function clearForm() {

    document.getElementById(
        "bookId"
    ).value = "";


    document.getElementById(
        "title"
    ).value = "";


    document.getElementById(
        "author"
    ).value = "";


    document.getElementById(
        "isbn"
    ).value = "";


    document.getElementById(
        "category"
    ).value = "";


    document.getElementById(
        "quantity"
    ).value = "";


    document.getElementById(
        "addButton"
    ).style.display = "inline-block";


    document.getElementById(
        "updateButton"
    ).style.display = "none";

}


// ==========================================
// LOAD BOOKS WHEN PAGE OPENS
// ==========================================

document.addEventListener(
    "DOMContentLoaded",
    function () {

        loadBooks();

    }
);