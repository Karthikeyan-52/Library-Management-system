// ==========================================
// API URL
// ==========================================

const ISSUE_API = "/api/issues";
const BOOK_API = "/api/books";
const MEMBER_API = "/api/members";


// ==========================================
// LOAD MEMBERS
// ==========================================

function loadMembers() {

    fetch(MEMBER_API)
        .then(response => response.json())
        .then(members => {

            const memberSelect =
                document.getElementById("memberId");

            memberSelect.innerHTML =
                '<option value="">Select Member</option>';

            members.forEach(member => {

                if (member.status === "ACTIVE") {

                    const option =
                        document.createElement("option");

                    option.value =
                        member.memberId;

                    option.textContent =
                        member.name;

                    memberSelect.appendChild(option);
                }

            });

        })
        .catch(error => {

            console.error(
                "Error loading members:",
                error
            );

        });
}


// ==========================================
// LOAD AVAILABLE BOOKS
// ==========================================

function loadBooks() {

    fetch(BOOK_API)
        .then(response => response.json())
        .then(books => {

            const bookSelect =
                document.getElementById("bookId");

            bookSelect.innerHTML =
                '<option value="">Select Book</option>';

            books.forEach(book => {

                if (book.availableQuantity > 0) {

                    const option =
                        document.createElement("option");

                    option.value =
                        book.bookId;

                    option.textContent =
                        book.title +
                        " (Available: " +
                        book.availableQuantity +
                        ")";

                    bookSelect.appendChild(option);
                }

            });

        })
        .catch(error => {

            console.error(
                "Error loading books:",
                error
            );

        });
}


// ==========================================
// ISSUE BOOK
// ==========================================

function issueBook() {

    const bookId =
        document.getElementById("bookId").value;

    const memberId =
        document.getElementById("memberId").value;

    const dueDate =
        document.getElementById("dueDate").value;


    // Validate fields

    if (
        bookId === "" ||
        memberId === "" ||
        dueDate === ""
    ) {

        alert(
            "Please fill all fields"
        );

        return;
    }


    const issue = {

        bookId:
            parseInt(bookId),

        memberId:
            parseInt(memberId),

        dueDate:
            dueDate

    };


    fetch(ISSUE_API, {

        method: "POST",

        headers: {

            "Content-Type":
                "application/json"

        },

        body:
            JSON.stringify(issue)

    })

    .then(response => {

        if (!response.ok) {

            throw new Error(
                "Unable to issue book"
            );

        }

        return response.json();

    })

    .then(data => {

        alert(
            "Book issued successfully!"
        );

        clearIssueForm();

        loadBooks();

        loadIssues();

    })

    .catch(error => {

        console.error(error);

        alert(
            "Error issuing book"
        );

    });

}


// ==========================================
// LOAD ISSUE HISTORY
// ==========================================

function loadIssues() {

    fetch(ISSUE_API)

        .then(response =>
            response.json()
        )

        .then(issues => {

            displayIssues(issues);

        })

        .catch(error => {

            console.error(
                "Error loading issues:",
                error
            );

        });
}


// ==========================================
// DISPLAY ISSUE HISTORY
// ==========================================

function displayIssues(issues) {

    const tableBody =
        document.getElementById(
            "issueTableBody"
        );


    tableBody.innerHTML = "";


    issues.forEach(issue => {

        const row =
            document.createElement("tr");


        let actionButton = "";


        // Show return button only
        // for issued books

        if (issue.status === "ISSUED") {

            actionButton = `

                <button
                    class="btn-success"
                    onclick="returnBook(${issue.issueId})">

                    Return

                </button>

            `;

        } else {

            actionButton = `
                <span>Returned</span>
            `;

        }


        row.innerHTML = `

            <td>
                ${issue.issueId}
            </td>

            <td>
                ${issue.bookId}
            </td>

            <td>
                ${issue.memberId}
            </td>

            <td>
                ${issue.issueDate || ""}
            </td>

            <td>
                ${issue.dueDate || ""}
            </td>

            <td>
                ${issue.returnDate || ""}
            </td>

            <td>
                ${issue.status}
            </td>

            <td>
                ₹${issue.fine}
            </td>

            <td>
                ${actionButton}
            </td>

        `;


        tableBody.appendChild(row);

    });

}


// ==========================================
// RETURN BOOK
// ==========================================

function returnBook(issueId) {

    const confirmReturn =
        confirm(
            "Are you sure you want to return this book?"
        );


    if (!confirmReturn) {

        return;

    }


    fetch(
        `${ISSUE_API}/${issueId}/return`,
        {

            method: "PUT"

        }
    )

    .then(response => {

        if (!response.ok) {

            throw new Error(
                "Unable to return book"
            );

        }

        return response.json();

    })

    .then(issue => {

        alert(
            "Book returned successfully!\n" +
            "Fine: ₹" +
            issue.fine
        );


        loadIssues();

        loadBooks();

    })

    .catch(error => {

        console.error(error);

        alert(
            "Error returning book"
        );

    });

}


// ==========================================
// CLEAR ISSUE FORM
// ==========================================

function clearIssueForm() {

    document.getElementById(
        "bookId"
    ).value = "";


    document.getElementById(
        "memberId"
    ).value = "";


    document.getElementById(
        "dueDate"
    ).value = "";

}


// ==========================================
// LOAD DATA WHEN PAGE OPENS
// ==========================================

document.addEventListener(
    "DOMContentLoaded",
    function () {

        loadMembers();

        loadBooks();

        loadIssues();

    }
);