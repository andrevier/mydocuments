// var title = document.getElementById("document-title");
// var content = document.getElementById("document-content");
// var saveButton = document.getElementById("save-button");

// const updateUrl = "http://localhost:8080/update-document";

// saveButton.addEventListener("click", function(e) {
//   e.preventDefault();

//   // Data form the document.
//   documentData.title = title.value;
//   documentData.content = content.value;

//   // let data = {
//   //   documentId: documentData.documentId,
//   //   title: title.value,
//   //   content: content.value,
//   //   username: documentData.username
//   // };
//   console.log(documentData);

//   fetch(updateUrl, {
//     method: 'PUT',
//     headers: {
//       'Content-type': 'application/json; charset=UTF-8'
//     },
//     body: JSON.stringify(documentData)
//   })
//   .then(response => console.log(response.json()))
//   .catch(err => console.log(err));
// })
