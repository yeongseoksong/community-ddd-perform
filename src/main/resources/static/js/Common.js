function extractCategoryId() {
     const path = window.location.pathname;
     const parts = path.split('/');
     const categorySlug = parts[2];
     return categorySlug;
 }

function showNotification(title, message, type) {
    const notificationContainer = document.createElement('div');
    notificationContainer.className = `fixed top-4 right-4 z-50 p-4 rounded-lg shadow-lg ${type === 'success' ? 'bg-green-100 border-l-4 border-green-500' : 'bg-red-100 border-l-4 border-red-500'}`;

    notificationContainer.innerHTML = `
                <div class="flex items-center">
                    <div class="flex-shrink-0">
                        ${type === 'success'
        ? '<svg class="w-5 h-5 text-green-700" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path></svg>'
        : '<svg class="w-5 h-5 text-red-700" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path></svg>'
    }
                    </div>
                    <div class="ml-3">
                        <h3 class="text-sm font-medium ${type === 'success' ? 'text-green-700' : 'text-red-700'}">${title}</h3>
                        <div class="mt-1 text-sm ${type === 'success' ? 'text-green-600' : 'text-red-600'}">${message}</div>
                    </div>
                    <div class="ml-auto pl-3">
                        <button type="button" class="inline-flex text-gray-400 hover:text-gray-500 focus:outline-none" onclick="this.parentElement.parentElement.parentElement.remove()">
                            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path></svg>
                        </button>
                    </div>
                </div>
            `;

    document.body.appendChild(notificationContainer);

    // 5초 후 자동으로 알림 제거
    setTimeout(() => {
        notificationContainer.remove();
    }, 3000);
}


function extractPostIdByDom(){
  return document.querySelector('input[name="postId"]')?.value
}

function saveResource(postId,file){
    return new Promise((resolve,reject) =>{const data = new FormData();
    data.append('upload', file);
    fetch(`/api/members/posts/${postId}/resources`, {
        method: 'POST',
        body: data,
    }).then(resp=>resp.json())
        .then(result => {
            resolve({
                default: result.data.url
            });
        })
        .catch(err=>{reject(err)});
    });
}

function savePost(postId,content,attachments) {
    const form = document.getElementById('postForm');
    if(!form) throw new Error("");
    const formData = new FormData();

    // JSON payload를 문자열로 변환 후 post-payload 파트에 추가
    const postPayload = {
        title: form.title.value,
        content: content,
        isPremium: form.isPremium.checked,
        categoryId: form.categoryId.value
    };
    formData.append("post-payload", new Blob(
        [JSON.stringify(postPayload)],
        { type: "application/json" }
    ));

    if (attachments && attachments.files.length > 0) {
        for (let i = 0; i < attachments.files.length; i++) {
            formData.append("attachments", attachments.files[i]);
        }
    }

    return fetch(`/api/members/posts/${postId}`, {
      method: 'PUT',
        body: formData
    }).then(res => {
      if (!res.ok) throw new Error('저장 실패');
      return res.json();
    });


}