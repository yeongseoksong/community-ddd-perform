function extractCategoryId() {
     const path = window.location.pathname;
     const parts = path.split('/');
     const categorySlug = parts[2];
     return categorySlug;
 }

//function extractPostIdByUrl(){
//   const path = window.location.pathname;
//   const parts = path.split('/');
//   const postId = parts[4]
//   return postId
//}
//
//function extractPostIdByDom(){
//   return document.querySelector('input[name="postId"]')?.value
//}


function savePost(postId,content) {
    const form = document.getElementById('postForm');
    if(!form) throw new Error("");
    const saveData = {
      title: form.title.value,
      content: content, // editorInstance.getData() 대신 임의로
      isPremium: form.isPremium.checked,
      categoryId: form.categoryId.value
    };
    return fetch(`/api/members/posts/${postId}`, {
      method: 'PUT',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(saveData)
    }).then(res => {
      if (!res.ok) throw new Error('저장 실패');
      return res.json();
    });
}