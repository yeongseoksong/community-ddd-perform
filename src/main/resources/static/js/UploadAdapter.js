class MyUploadAdapter {
    constructor(loader) {
        this.loader = loader;
    }

    upload() {
        return this.loader.file.then(file => {
            return new Promise((resolve, reject) => {
                const data = new FormData();
                data.append('upload', file);
//                data.append('author', 'user123'); // 👈 원하는 body 추가

                fetch('/api/resources', {
                    method: 'POST',
                    body: data,
                })
                    .then(response => response.json())
                    .then(result => {
                        resolve({
                            default: result.url // 👈 이미지 경로 반환
                        });
                    })
                    .catch(err => {
                        reject(err);
                    });
            });
        });
    }
    abort() {
    }
}

function uploadAdapterPlugin(editor) {
    editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
        return new MyUploadAdapter(loader);
    };
}