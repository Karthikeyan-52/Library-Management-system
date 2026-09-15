// ==========================================
// MEMBER API URL
// ==========================================

const MEMBER_API = "/api/members";


// ==========================================
// LOAD ALL MEMBERS
// ==========================================

function loadMembers() {

    fetch(MEMBER_API)

        .then(response => {

            if (!response.ok) {

                throw new Error(
                    "Failed to load members"
                );

            }

            return response.json();

        })

        .then(members => {

            displayMembers(members);

        })

        .catch(error => {

            console.error(
                "Error loading members:",
                error
            );

            alert(
                "Unable to load members"
            );

        });
}


// ==========================================
// DISPLAY MEMBERS
// ==========================================

function displayMembers(members) {

    const tableBody =
        document.getElementById(
            "memberTableBody"
        );


    tableBody.innerHTML = "";


    members.forEach(member => {

        const row =
            document.createElement("tr");


        row.innerHTML = `

            <td>
                ${member.memberId}
            </td>

            <td>
                ${member.name}
            </td>

            <td>
                ${member.email}
            </td>

            <td>
                ${member.phone || ""}
            </td>

            <td>

                <span class="status status-active">

                    ${member.status}

                </span>

            </td>

            <td>

                <button
                    class="btn-warning"
                    onclick="editMember(${member.memberId})">

                    Edit

                </button>


                <button
                    class="btn-danger"
                    onclick="deleteMember(${member.memberId})">

                    Delete

                </button>

            </td>

        `;


        tableBody.appendChild(row);

    });

}


// ==========================================
// ADD NEW MEMBER
// ==========================================

function addMember() {

    const name =
        document.getElementById(
            "name"
        ).value.trim();


    const email =
        document.getElementById(
            "email"
        ).value.trim();


    const phone =
        document.getElementById(
            "phone"
        ).value.trim();


    // Validate fields

    if (
        name === "" ||
        email === "" ||
        phone === ""
    ) {

        alert(
            "Please fill all fields"
        );

        return;

    }


    const member = {

        name: name,

        email: email,

        phone: phone,

        status: "ACTIVE"

    };


    fetch(MEMBER_API, {

        method: "POST",

        headers: {

            "Content-Type":
                "application/json"

        },

        body:
            JSON.stringify(member)

    })

    .then(response => {

        if (!response.ok) {

            throw new Error(
                "Failed to add member"
            );

        }

        return response.json();

    })

    .then(data => {

        alert(
            "Member added successfully!"
        );


        clearMemberForm();


        loadMembers();

    })

    .catch(error => {

        console.error(error);

        alert(
            "Error adding member"
        );

    });

}


// ==========================================
// EDIT MEMBER
// ==========================================

function editMember(id) {

    fetch(
        `${MEMBER_API}/${id}`
    )

        .then(response =>
            response.json()
        )

        .then(member => {

            document.getElementById(
                "memberId"
            ).value =
                member.memberId;


            document.getElementById(
                "name"
            ).value =
                member.name;


            document.getElementById(
                "email"
            ).value =
                member.email;


            document.getElementById(
                "phone"
            ).value =
                member.phone;


            document.getElementById(
                "status"
            ).value =
                member.status;


            // Hide Add button

            document.getElementById(
                "addButton"
            ).style.display =
                "none";


            // Show Update button

            document.getElementById(
                "updateButton"
            ).style.display =
                "inline-block";

        })

        .catch(error => {

            console.error(error);

            alert(
                "Unable to load member"
            );

        });

}


// ==========================================
// UPDATE MEMBER
// ==========================================

function updateMember() {

    const id =
        document.getElementById(
            "memberId"
        ).value;


    const member = {

        name:
            document.getElementById(
                "name"
            ).value.trim(),

        email:
            document.getElementById(
                "email"
            ).value.trim(),

        phone:
            document.getElementById(
                "phone"
            ).value.trim(),

        status:
            document.getElementById(
                "status"
            ).value

    };


    fetch(
        `${MEMBER_API}/${id}`,
        {

            method: "PUT",

            headers: {

                "Content-Type":
                    "application/json"

            },

            body:
                JSON.stringify(member)

        }
    )

    .then(response => {

        if (!response.ok) {

            throw new Error(
                "Failed to update member"
            );

        }

        return response.json();

    })

    .then(data => {

        alert(
            "Member updated successfully!"
        );


        clearMemberForm();


        loadMembers();

    })

    .catch(error => {

        console.error(error);

        alert(
            "Error updating member"
        );

    });

}


// ==========================================
// DELETE MEMBER
// ==========================================

function deleteMember(id) {

    const confirmDelete =
        confirm(
            "Are you sure you want to delete this member?"
        );


    if (!confirmDelete) {

        return;

    }


    fetch(
        `${MEMBER_API}/${id}`,
        {

            method: "DELETE"

        }
    )

    .then(response => {

        if (!response.ok) {

            throw new Error(
                "Failed to delete member"
            );

        }

        return response.text();

    })

    .then(message => {

        alert(message);


        loadMembers();

    })

    .catch(error => {

        console.error(error);

        alert(
            "Error deleting member"
        );

    });

}


// ==========================================
// SEARCH MEMBERS
// ==========================================

function searchMembers() {

    const keyword =
        document.getElementById(
            "searchInput"
        ).value.trim();


    // If search box is empty
    // load all members

    if (keyword === "") {

        loadMembers();

        return;

    }


    fetch(
        `${MEMBER_API}/search?keyword=${encodeURIComponent(keyword)}`
    )

        .then(response =>
            response.json()
        )

        .then(members => {

            displayMembers(members);

        })

        .catch(error => {

            console.error(error);

            alert(
                "Error searching members"
            );

        });

}


// ==========================================
// CLEAR MEMBER FORM
// ==========================================

function clearMemberForm() {

    document.getElementById(
        "memberId"
    ).value = "";


    document.getElementById(
        "name"
    ).value = "";


    document.getElementById(
        "email"
    ).value = "";


    document.getElementById(
        "phone"
    ).value = "";


    document.getElementById(
        "status"
    ).value = "ACTIVE";


    // Show Add button

    document.getElementById(
        "addButton"
    ).style.display =
        "inline-block";


    // Hide Update button

    document.getElementById(
        "updateButton"
    ).style.display =
        "none";

}


// ==========================================
// LOAD MEMBERS WHEN PAGE OPENS
// ==========================================

document.addEventListener(
    "DOMContentLoaded",
    function () {

        loadMembers();

    }
);