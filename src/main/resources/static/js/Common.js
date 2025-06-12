function extractCategoryId() {
    const path = window.location.pathname;
    const parts = path.split('/');
    const categorySlug = parts[2];
    return categorySlug;
}
    // window.location.href = `/categories/${categorySlug}/posts/write`}
