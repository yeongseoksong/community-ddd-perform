//package com.portfolio.community.resource.infra;
//
//import com.portfolio.community.resource.domain.StorageStrategy;
//
//public class RemoteSave implements StorageStrategy {
//
//    private final S3Client s3Client;
//    private final String bucketName;
//
//    public RemoteSave(S3Client s3Client, String bucketName) {
//        this.s3Client = s3Client;
//        this.bucketName = bucketName;
//    }
//
//    @Override
//    public void save(byte[] data, String filename) {
//        PutObjectRequest putRequest = PutObjectRequest.builder()
//                .bucket(bucketName)
//                .key(filename)
//                .build();
//
//        s3Client.putObject(putRequest, RequestBody.fromBytes(data));
//        System.out.println("Saved to S3: " + filename);
//    }
//
//    @Override
//    public void delete(String filename) {
//        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
//                .bucket(bucketName)
//                .key(filename)
//                .build();
//
//        s3Client.deleteObject(deleteRequest);
//        System.out.println("Deleted from S3: " + filename);
//    }
//}
