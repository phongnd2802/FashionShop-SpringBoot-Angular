package com.caonguyen.fashionshop.services.impl;

import com.caonguyen.fashionshop.dtos.request.product.*;
import com.caonguyen.fashionshop.dtos.response.product.*;
import com.caonguyen.fashionshop.entities.product.CategoryEntity;
import com.caonguyen.fashionshop.entities.product.SKUEntity;
import com.caonguyen.fashionshop.entities.product.SPUEntity;
import com.caonguyen.fashionshop.repositories.CategoryRepository;
import com.caonguyen.fashionshop.repositories.SKURepository;
import com.caonguyen.fashionshop.repositories.SPURepository;
import com.caonguyen.fashionshop.services.IProductService;
import com.caonguyen.fashionshop.services.other.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SPURepository spuRepository;

    @Autowired
    private SKURepository skuRepository;

    @Autowired
    private FileUploadService fileUploadService;

    @Transactional
    @Override
    public CreateProductRes createProduct(CreateProductRequest req) {
        // Get thumb SPU
        MultipartFile file = req.getFileThumb();
        if (file != null && !file.isEmpty()) {
            String fileName = fileUploadService.uploadFile(file);
            // CHANGE newFilename IF DEPLOY SERVER
            String newFilename = "http://localhost:9000/products/" + fileName;
            newFilename.replace(" ", "%");
            try {
                req.setSpuThumb(newFilename);
            } catch (Exception e) {
                CreateProductRes res = new CreateProductRes();
                res.setSuccess(Boolean.FALSE);
                res.setMessage("Thêm ảnh thumb không thành công!");
                return res;
            }
        }

        // Get list image of SKU
        List<MultipartFile> fileList = req.getFileImages();
        List<String> spuList = new ArrayList<>();
        if (fileList != null && !fileList.isEmpty()) {
            for (MultipartFile item : fileList) {
                String fileName = fileUploadService.uploadFile(item);
                // CHANGE newFilename IF DEPLOY SERVER
                String newFilename = "http://localhost:9000/products/" + fileName;
                newFilename.replace(" ", "%");
                spuList.add(newFilename);
            }
            try {
                req.setSpuImages(spuList);
            } catch (Exception e) {
                CreateProductRes res = new CreateProductRes();
                res.setSuccess(Boolean.FALSE);
                res.setMessage("Thêm ảnh sản phẩm không thành công!");
                return res;
            }
        }

        // Create category if not exist db
        CategoryEntity categoryEntity = categoryRepository.findByName(req.getCategoryName());
        if (categoryEntity == null) {
            categoryEntity = insertCategory(req.getCategoryName());
        }

        // Create SPU if not exist db
        String spuCode = req.getSpuCode();
        SPUEntity spuEntity = spuRepository.findByCode(spuCode);
        if (spuEntity == null) {
            if (req.getSpuThumb() != null) {
                spuEntity = insertSPU(
                        req.getSpuCode(),
                        req.getSpuName(),
                        req.getSpuCurrentPrice(),
                        req.getSpuOriginalPrice(),
                        req.getSpuDescription(),
                        false,
                        req.getSpuSort(),
                        req.isSpuStatus(),
                        req.getSpuThumb()
                );
                spuEntity.setCategory(categoryEntity);
                spuRepository.save(spuEntity);
            } else {
                CreateProductRes res = new CreateProductRes();
                res.setSuccess(Boolean.FALSE);
                res.setMessage("Chưa thêm ảnh thumb cho SPU!");
                return res;
            }
        }

        // Create SKU
        SKUEntity skuEntity = insertSKU(
                req.getSkuCode(),
                req.getSkuColor(),
                req.getSkuCurrentPrice(),
                req.getSkuOriginalPrice(),
                req.getSkuSize(),
                req.getSkuStock()
        );
        try {
            skuEntity.setImages(req.getSpuImages());
        } catch (Exception e) {
            CreateProductRes res = new CreateProductRes();
            res.setSuccess(Boolean.FALSE);
            res.setMessage("Chưa thêm ảnh sản phẩm!");
            return res;
        }
        skuEntity.setSpu(spuEntity);
        skuRepository.save(skuEntity);

        // Respone
        CreateProductRes res = new CreateProductRes();
        res.setSuccess(Boolean.TRUE);
        res.setMessage("Thêm sản phẩm thành công!");
        return res;
    }

    private CategoryEntity insertCategory(String name){
        CategoryEntity category = new CategoryEntity();
        category.setName(name);
        return categoryRepository.save(category);
    }

    private SPUEntity insertSPU(String code, String name, int currentPrice, int originalPrice, String description,
                                boolean isPublish, int sort, boolean status, String thumb){
        SPUEntity spu = new SPUEntity();
        spu.setCode(code);
        spu.setName(name);
        spu.setCurrentPrice(currentPrice);
        spu.setOriginalPrice(originalPrice);
        spu.setDescription(description);
        spu.setPublished(isPublish);
        spu.setSort(sort);
        spu.setStatus(status);
        spu.setThumbnail(thumb);
        return spu;
    }

    private SKUEntity insertSKU(String code, String color, int currentPrice, int originalPrice, String size, int stock){
        SKUEntity sku = new SKUEntity();
        sku.setCode(code);
        sku.setColor(color);
        sku.setCurrentPrice(currentPrice);
        sku.setOriginalPrice(originalPrice);
        sku.setSize(size);
        sku.setStock(stock);
        return sku;
    }

    @Transactional
    @Override
    public UpdateProductRes updateProduct(UpdateProductRequest req) {
        SPUEntity spuEntity = spuRepository.findByCode(req.getSpuCode());
        if (spuEntity != null) {
            SKUEntity skuEntity = skuRepository.findByCode(req.getSkuCode());
            if (skuEntity != null) {
                if (req.getSpuCurrentPrice() != 0) {
                    spuEntity.setCurrentPrice(req.getSpuCurrentPrice());
                }
                if (req.getSpuOriginalPrice() != 0) {
                    spuEntity.setOriginalPrice(req.getSpuOriginalPrice());
                }
                if (req.getSpuName() != null) {
                    spuEntity.setName(req.getSpuName());
                }
                if (req.getSpuDescription() != null) {
                    spuEntity.setDescription(req.getSpuDescription());
                }
                if (req.getSpuSort() != 0) {
                    spuEntity.setSort(req.getSpuSort());
                }
                if (req.isSpuStatus() != true) {
                    spuEntity.setStatus(req.isSpuStatus());
                }
                if (req.getSpuThumb() != null) {
                    spuEntity.setThumbnail(req.getSpuThumb());
                }
                if (req.getSpuImages() != null) {
                    skuEntity.setImages(req.getSpuImages());
                }
                if (req.getSkuCurrentPrice() != 0) {
                    skuEntity.setCurrentPrice(req.getSkuCurrentPrice());
                }
                if (req.getSkuOriginalPrice() != 0) {
                    skuEntity.setOriginalPrice(req.getSkuOriginalPrice());
                }
                if (req.getSkuStock() != 0) {
                    skuEntity.setStock(req.getSkuStock());
                }

                spuRepository.save(spuEntity);
                skuRepository.save(skuEntity);

                UpdateProductRes res = new UpdateProductRes();
                res.setSuccess(Boolean.TRUE);
                res.setMessage("Chỉnh sửa sản phẩm thành công!");
                return res;
            } else {
                UpdateProductRes res = new UpdateProductRes();
                res.setSuccess(Boolean.FALSE);
                res.setMessage("Không tìm thấy SKU!");
                return res;
            }

        } else {
            UpdateProductRes res = new UpdateProductRes();
            res.setSuccess(Boolean.FALSE);
            res.setMessage("Không tìm thấy SPU!");
            return res;
        }
    }

    @Transactional
    @Override
    public DeleteProductRes deleteProduct(DeleteProductRequest req) {
        SPUEntity spuEntity = spuRepository.findByCode(req.getSpuCode());
        if (spuEntity != null) {
            if (req.isSpuIsDeleted() == true) {
                List<SKUEntity> skuEntityList = skuRepository.findBySpu(spuEntity);
                if (skuEntityList.size() > 0) {
                    for (SKUEntity skuEntity : skuEntityList) {
                        skuEntity.setDeleted(true);
                        skuRepository.save(skuEntity);
                    }
                }
                spuEntity.setDeleted(true);
                spuRepository.save(spuEntity);

                DeleteProductRes res = new DeleteProductRes();
                res.setSuccess(Boolean.TRUE);
                res.setMessage("Xóa SPU thành công!");
                return res;
            } else {
                SKUEntity skuEntity = skuRepository.findByCode(req.getSkuCode());
                if (skuEntity != null) {
                    skuEntity.setDeleted(true);
                    skuRepository.save(skuEntity);

                    DeleteProductRes res = new DeleteProductRes();
                    res.setSuccess(Boolean.TRUE);
                    res.setMessage("Xóa SKU thành công!");
                    return res;
                } else {
                    DeleteProductRes res = new DeleteProductRes();
                    res.setSuccess(Boolean.FALSE);
                    res.setMessage("Không tìm thấy SKU!");
                    return res;
                }
            }
        } else {
            DeleteProductRes res = new DeleteProductRes();
            res.setSuccess(Boolean.FALSE);
            res.setMessage("Không tìm thấy SPU!");
            return res;
        }
    }

    @Override
    public List<SearchSPURes> searchSPU(SearchSPURequest req) {
        List<SPUEntity> results = new ArrayList<>();

        if (req.getName() != null && !req.getName().isEmpty() && req.getPrice() != null && !req.getPrice().isEmpty()) {
            int[] listPrice = splitPrice(req.getPrice());
            int minPrice = listPrice[0];
            int maxPrice = listPrice[1];
            results = spuRepository.findByNameContainingIgnoreCaseAndCurrentPriceBetween(req.getName(), minPrice, maxPrice);
        } else if (req.getName() != null && !req.getName().isEmpty()) {
            results = spuRepository.findByNameContainingIgnoreCase(req.getName());
        } else if (req.getPrice() != null && !req.getPrice().isEmpty()) {
            int[] listPrice = splitPrice(req.getPrice());
            int minPrice = listPrice[0];
            int maxPrice = listPrice[1];
            results = spuRepository.findByCurrentPriceBetween(minPrice, maxPrice);
        }

        List<SearchSPURes> resList = new ArrayList<>();
        for (SPUEntity spuEntity : results) {
            SearchSPURes res = new SearchSPURes();
            res.setCode(spuEntity.getCode());
            res.setName(spuEntity.getName());
            res.setDescription(spuEntity.getDescription());
            res.setCurrentPrice(spuEntity.getCurrentPrice());
            res.setOriginalPrice(spuEntity.getOriginalPrice());
            res.setIsPublished(spuEntity.isPublished());
            res.setSort(spuEntity.getSort());
            res.setStatus(spuEntity.isStatus());
            res.setThumb(spuEntity.getThumbnail());
            res.setCategoryId(spuEntity.getCategory() != null ? spuEntity.getCategory().getId() : null);
            resList.add(res);
        }

        return resList;
    }

    private int parsePrice(String price) {
        String numericPrice = price.replaceAll("[^\\d]", "");
        return numericPrice.isEmpty() ? 0 : Integer.parseInt(numericPrice);
    }

    private int[] splitPrice(String price) {
        int[] listPrice = new int[2];
        int minPrice = 0;
        int maxPrice = Integer.MAX_VALUE;

        String priceStr = price;
        if (priceStr.contains("-")) {
            String[] prices = priceStr.split("-");
            minPrice = parsePrice(prices[0]);
            maxPrice = parsePrice(prices[1]);
        } else if (priceStr.contains("Dưới")) {
            maxPrice = parsePrice(priceStr.replace("Dưới", ""));
        } else if (priceStr.contains("Trên")) {
            minPrice = parsePrice(priceStr.replace("Trên", ""));
        }

        listPrice[0] = minPrice;
        listPrice[1] = maxPrice;
        return listPrice;
    }

    @Override
    public List<SearchSKURes> searchSKU(SearchSKURequest req) {
        List<SKUEntity> results = new ArrayList<>();
        results = skuRepository.findBySpuCode(req.getSpuCode());
        List<SearchSKURes> resList = new ArrayList<>();
        for (SKUEntity skuEntity : results) {
            List<String> listImages = skuEntity.getImages();

            SearchSKURes res = new SearchSKURes();
            res.setCode(skuEntity.getCode());
            res.setColor(skuEntity.getColor());
            res.setCurrentPrice(skuEntity.getCurrentPrice());
            res.setOriginalPrice(skuEntity.getOriginalPrice());
            res.setSize(skuEntity.getSize());
            res.setStock(skuEntity.getStock());
            res.setDeleted(skuEntity.isDeleted());
            res.setImages(listImages);
            resList.add(res);
        }
        return resList;
    }
}